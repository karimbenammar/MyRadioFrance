package com.example.myradiofrance.di

import com.apollographql.apollo3.ApolloClient
import com.example.myradiofrance.data.ApolloBrandClient
import com.example.myradiofrance.data.ApolloShowsClient
import com.example.myradiofrance.domain.BrandClient
import com.example.myradiofrance.domain.GetBrandsUseCase
import com.example.myradiofrance.domain.GetShowsUseCase
import com.example.myradiofrance.domain.ShowsClient
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
    fun provideShowsClient(apolloClient: ApolloClient): ShowsClient {
        return ApolloShowsClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetBrandsUseCase(brandClient: BrandClient): GetBrandsUseCase {
        return GetBrandsUseCase(brandClient)
    }

    @Provides
    @Singleton
    fun provideGetShowsUseCase(showsClient: ShowsClient): GetShowsUseCase {
        return GetShowsUseCase(showsClient)
    }
}
