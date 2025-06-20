package com.example.atonce_admin.data.repository

import com.example.atonce_admin.data.remote.RemoteDataSource
import com.example.atonce_admin.domain.repository.Repository

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : Repository{

}