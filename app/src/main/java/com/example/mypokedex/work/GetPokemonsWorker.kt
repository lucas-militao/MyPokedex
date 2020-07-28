package com.example.mypokedex.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class GetPokemonsWorker(ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        TODO("Not yet implemented")
    }

}