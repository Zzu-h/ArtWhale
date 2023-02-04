package com.capstone.artwhale.data.repository

import com.capstone.artwhale.data.datasource.user.UserDataSource
import com.capstone.artwhale.data.dto.toUpdateNicknameDto
import com.capstone.artwhale.domain.model.UserInfo
import com.capstone.artwhale.domain.repository.UserRepository
import com.capstone.artwhale.util.LocalPathUtil
import java.io.File
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localPathUtil: LocalPathUtil,
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun getMyInfo(): UserInfo = userDataSource.getMyInfo().toUserInfo()

    override suspend fun updateNickName(nickName: String) =
        userDataSource.updateNickName(nickName.toUpdateNicknameDto())

    override suspend fun updateProfileImage(uri: String) {
        localPathUtil.getRealPathFromUriString(uri)
            ?.let { userDataSource.updateProfileImage("image", File(it)) }
    }
}