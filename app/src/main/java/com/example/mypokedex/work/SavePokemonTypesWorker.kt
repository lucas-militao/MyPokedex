package com.example.mypokedex.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.mypokedex.usecase.SavePokemonTypesUseCase
import java.lang.Exception

class SavePokemonTypesWorker(ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params) {

    private val useCase = SavePokemonTypesUseCase(ctx)

    override suspend fun doWork(): Result {
        return try {
            useCase.execute()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

}