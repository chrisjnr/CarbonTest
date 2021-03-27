package com.loveworldapps.carbontest.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.paging.PagedList
import androidx.transition.TransitionInflater
import com.example.awesomedialog.*
import com.loveworldapps.carbontest.databinding.FragmentUserBinding
import com.loveworldapps.domain.model.User
import com.loveworldapps.carbontest.ui.UserViewModel
import com.loveworldapps.carbontest.ui.adapter.UsersAdapter
import org.koin.android.viewmodel.ext.android.sharedViewModel
import java.util.*
import com.loveworldapps.carbontest.R
import com.loveworldapps.domain.model.Constants

/**
 * Created by manuelchris-ogar on 27/03/2021.
 */
class UserFragment : Fragment() {

    private lateinit var storedUserList: PagedList<User>
    private val _binding  by  lazy { FragmentUserBinding.inflate(layoutInflater) }
    private val binding get() = _binding

    lateinit var adapter: UsersAdapter

    private val viewModel: UserViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = UsersAdapter( object :   UsersAdapter.onClickListener{
            override fun viewMore(user: User) {
                viewModel.selectedUser.postValue(user)
                requireActivity().findNavController(R.id.nav_host).navigate(R.id.userDetailFragment)
            }

        })

        binding.list.adapter = adapter
        setupObservers()
    }

    private fun setupObservers() {

        viewModel.storedUsers.observe(viewLifecycleOwner, Observer{

            storedUserList = it
            adapter.submitList(it)
        })


        viewModel.progressLoadStatus.observe(viewLifecycleOwner, { status ->
            when {
                Objects.requireNonNull(status) ==(Constants.LOADING) -> {
                    binding.progress.visibility = View.VISIBLE
                }
                status ==(Constants.LOADED) -> {
                    binding.progress.visibility = View.GONE
                }
                status== Constants.CHECK_NETWORK_ERROR -> {
                    binding.progress.visibility = View.GONE
                    AwesomeDialog.build(requireActivity())
                        .title("Error")
                        .body(Constants.CHECK_NETWORK_ERROR)
                        .icon(R.drawable.ic_wifi, animateIcon = true)
                        .onPositive("Okay"){

                        }

                }
            }
        })
    }

}