package com.example.myradiofrance.di

import com.apollographql.apollo3.ApolloClient
import com.example.myradiofrance.data.ApolloBrandClient
import com.example.myradiofrance.domain.BrandClient
import com.example.myradiofrance.domain.GetBrandsUseCase
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
            .serverUrl("https://openapi.radiofrance.fr/v1/graphql?x-token=84c107b0-22f0-4958-883d-381edaa54174")
            .build()
    }

    @Provides
    @Singleton
    fun provideBrandClient(apolloClient: ApolloClient): BrandClient {
        return ApolloBrandClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetBrandsUseCase(brandClient: BrandClient): GetBrandsUseCase {
        return GetBrandsUseCase(brandClient)
    }
}
