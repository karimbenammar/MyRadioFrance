package com.example.myradiofrance.di

import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.example.myradiofrance.R
import com.example.myradiofrance.data.remote.BrandRepositoryImpl
import com.example.myradiofrance.data.remote.ShowsRepositoryImpl
import com.example.myradiofrance.domain.repository.BrandRepository
import com.example.myradiofrance.domain.repository.ShowsRepository
import com.example.myradiofrance.domain.usecase.GetBrandsUseCase
import com.example.myradiofrance.domain.usecase.GetShowsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(@ApplicationContext context: Context): ApolloClient {
        val token = context.resources.getString(R.string.graphql_x_token)
        return ApolloClient.Builder().serverUrl(
            "https://openapi.radiofrance.fr/v1/graphql?x-token=${token}"
        ).build()
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
