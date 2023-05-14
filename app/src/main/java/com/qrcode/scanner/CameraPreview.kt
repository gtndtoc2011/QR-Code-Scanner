package com.qrcode.scanner

import android.view.ViewGroup
import androidx.camera.core.CameraSelector.DEFAULT_BACK_CAMERA
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.camera.view.CameraController.COORDINATE_SYSTEM_VIEW_REFERENCED
import com.google.mlkit.vision.barcode.common.Barcode
import com.qrcode.scanner.googlelib.QrCodeDrawable
import com.qrcode.scanner.googlelib.QrCodeViewModel
import kotlinx.coroutines.*


@Composable
fun CameraPreview(modifier: Modifier, onUrlDetect: (str: String) -> Unit = {}) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    AndroidView(
        factory = { AndroidViewContext ->
            PreviewView(AndroidViewContext).apply {
                this.scaleType = PreviewView.ScaleType.FILL_CENTER
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                )
                implementationMode = PreviewView.ImplementationMode.COMPATIBLE
            }
        },
        modifier = modifier
//            .fillMaxSize()
            .padding(50.dp)
    ) { previewView ->

        var barcodeScanner: BarcodeScanner? = null
        val cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()
        val cameraController = LifecycleCameraController(context)
        cameraController.cameraSelector = DEFAULT_BACK_CAMERA
        cameraController.bindToLifecycle(lifecycleOwner)
        previewView.controller = cameraController
        barcodeScanner?.close()

        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
            .build()

        barcodeScanner = BarcodeScanning.getClient(options)
        cameraController.clearImageAnalysisAnalyzer()
        cameraController.setImageAnalysisAnalyzer(
            cameraExecutor,
            MlKitAnalyzer(
                listOf(barcodeScanner),
                COORDINATE_SYSTEM_VIEW_REFERENCED,
                cameraExecutor
            ){ result ->
                val barcodeResults = result.getValue(barcodeScanner)
                if(barcodeResults == null || barcodeResults.size == 0 || barcodeResults.first() == null){
                    runBlocking {
                        withContext(Dispatchers.Main){
                            previewView.overlay.clear()
                        }
                    }
                    previewView.setOnTouchListener{ _,_ -> false } // no-op
                    return@MlKitAnalyzer
                }
                val qrCodeViewModel = QrCodeViewModel(barcodeResults[0])
                val qrCodeDrawable = QrCodeDrawable(qrCodeViewModel)
                previewView.setOnTouchListener(qrCodeViewModel.qrCodeTouchCallback)

                runBlocking {
                    withContext(Dispatchers.Main){
                        previewView.overlay.clear()
                        previewView.overlay.add(qrCodeDrawable)
                        if(qrCodeViewModel.qrContent.startsWith("http")){
                            onUrlDetect(qrCodeViewModel.qrContent)
                        }
                    }
                }
            }
        )
    }
}



