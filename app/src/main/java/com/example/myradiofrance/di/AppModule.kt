package com.example.myradiofrance.di

import com.apollographql.apollo3.ApolloClient
import com.example.myradiofrance.data.remote.BrandRepositoryImpl
import com.example.myradiofrance.data.remote.ShowsRepositoryImpl
import com.example.myradiofrance.domain.repository.BrandRepository
import com.example.myradiofrance.domain.repository.ShowsRepository
import com.example.myradiofrance.domain.usecase.GetBrandsUseCase
import com.example.myradiofrance.domain.usecase.GetShowsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://openapi.radiofrance.fr/v1/graphql?x-token=e59800c2-7e6d-4426-98b4-8a389c189569")
            .build()
    }

    @Provides
    @Singleton
    fun provideBrandClient(apolloClient: ApolloClient): BrandRepository {
        return BrandRepositoryImpl(apolloClient)
    }

    @Provides
    @Singleton
    fun provideShowsClient(apolloClient: ApolloClient): ShowsRepository {
        return ShowsRepositoryImpl(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetBrandsUseCase(brandRepository: BrandRepository): GetBrandsUseCase {
        return GetBrandsUseCase(brandRepository)
    }

    @Provides
    @Singleton
    fun provideGetShowsUseCase(showsRepository: ShowsRepository): GetShowsUseCase {
        return GetShowsUseCase(showsRepository)
    }
}
