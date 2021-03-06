package com.loveworldapps.data

import androidx.paging.DataSource
import com.loveworldapps.data.local.db.UsersDatabase
import com.loveworldapps.data.mapper.UserEntityMapper
import com.loveworldapps.data.mapper.entity.UserEntity
import com.loveworldapps.data.remote.CarbonApi
import com.loveworldapps.domain.model.User
import com.loveworldapps.domain.model.UserResponse
import com.loveworldapps.domain.model.repository.base.UserRepository
import io.reactivex.Observable

/**
 * Created by manuelchris-ogar on 27/03/2021.
 */
class UserRepositoryImpl(private val carbonApi: CarbonApi, private val db: UsersDatabase, val userEntityMapper: UserEntityMapper) : UserRepository {


    override fun loadUsers(limit: Int, page:Int): Observable<UserResponse?>? =  carbonApi.fetchUsers(limit, page = page)



    override fun addAllUsers(users: List<User>) = db.usersDao.addAllUsers(userEntityMapper.mapFromDomainList(users))

    override fun addUser(user:User) = db.usersDao.insert(userEntityMapper.mapToEntity(user))


    override fun getUserNumber():Int{
        return db.usersDao.getCount();
    }

    override fun getAllUsers(): DataSource.Factory<Int, User> = db.usersDao.getAll().map { userEntityMapper.mapFromEntity(it) }
    override fun searchForUser(query: String): List<User> = userEntityMapper.mapFromEntityList(db.usersDao.searchForUser(query) as List<UserEntity>)


}