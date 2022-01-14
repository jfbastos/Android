package br.com.iesb.maximaapp

import android.content.Context
import androidx.room.Room
import br.com.iesb.maximaapp.model.Repositorio
import br.com.iesb.maximaapp.model.database.AppDatabase
import br.com.iesb.maximaapp.model.database.ClienteDao
import br.com.iesb.maximaapp.model.database.PedidoDao
import br.com.iesb.maximaapp.model.network.MaximaApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuloApp {

    private const val BASE_URL = "https://private-anon-b1e0ede93c-maximatech.apiary-mock.com/android/"

    @Singleton
    @Provides
    fun forneceBanco(
        @ApplicationContext contextoDoApp: Context
    ) = Room.databaseBuilder(contextoDoApp, AppDatabase::class.java, "maximaAppDb" ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun forneceClienteDao(db : AppDatabase) = db.clienteDao()

    @Singleton
    @Provides
    fun fornecePedidoDao(db: AppDatabase) = db.pedidoDao()

    @Singleton
    @Provides
    fun logHttp() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun clienteHttp(httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun proveRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun proveServicoApi(retrofit: Retrofit) = retrofit.create(MaximaApi::class.java)

    @Singleton
    @Provides
    fun proveRepositorio(maximaApi : MaximaApi,clienteDao : ClienteDao, pedidoDao : PedidoDao) = Repositorio(maximaApi,clienteDao, pedidoDao)

}