package com.capstone.artwhale.domain.usecase.user.impl

import com.capstone.artwhale.domain.repository.UserRepository
import com.capstone.artwhale.domain.usecase.user.UpdateNickNameUseCase
import javax.inject.Inject

class UpdateNickNameUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : UpdateNickNameUseCase {

    override suspend operator fun invoke(nickName: String): Result<Unit> =
        runCatching { userRepository.updateNickName(nickName) }
}