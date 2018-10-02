package com.example.bd0631.goldseeker.dagger

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class FireStorageModule {

  @Module
  companion object {
    @Provides
    @Singleton
    @JvmStatic
    fun provideFirebaseStorage(): FirebaseFirestore {
      return FirebaseFirestore.getInstance()
    }
  }
}