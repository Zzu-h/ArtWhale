package com.capstone.artwhale.domain.usecase.user.impl

import com.capstone.artwhale.domain.model.UserInfo
import com.capstone.artwhale.domain.repository.UserRepository
import com.capstone.artwhale.domain.usecase.user.GetMyInfoUseCase
import javax.inject.Inject

class GetMyInfoUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : GetMyInfoUseCase {

    override suspend operator fun invoke(): Result<UserInfo> =
        runCatching { userRepository.getMyInfo() }
}