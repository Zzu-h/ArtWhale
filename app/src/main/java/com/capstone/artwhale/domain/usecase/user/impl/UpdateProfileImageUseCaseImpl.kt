package com.capstone.artwhale.domain.usecase.user.impl

import com.capstone.artwhale.domain.repository.UserRepository
import com.capstone.artwhale.domain.usecase.user.UpdateProfileImageUseCase
import javax.inject.Inject

class UpdateProfileImageUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : UpdateProfileImageUseCase {

    override suspend operator fun invoke(uri: String): Result<Unit> =
        runCatching { userRepository.updateProfileImage(uri) }
}