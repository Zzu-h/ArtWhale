package com.capstone.artwhale.data.repository

import com.capstone.artwhale.data.datasource.user.UserDataSource
import com.capstone.artwhale.domain.model.UserInfo
import com.capstone.artwhale.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun getMyInfo(): UserInfo = userDataSource.getMyInfo().toUserInfo()
}