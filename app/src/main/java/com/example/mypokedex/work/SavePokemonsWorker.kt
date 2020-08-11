package com.example.mypokedex.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.mypokedex.usecase.SavePokemonsUseCase
import java.lang.Exception

class SavePokemonsWorker(ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params) {

    val useCase = SavePokemonsUseCase(ctx)

    override suspend fun doWork(): Result {
        return try {
            useCase.execute()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

}