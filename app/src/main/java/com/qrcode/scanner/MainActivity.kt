package com.qrcode.scanner

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.qrcode.scanner.ui.theme.QRCodeScannerTheme

// [reference URL]
// Jetpack compose Barcode/QR code scanner
// https://www.codeplayon.com/jetpack-compose-barcode-qr-code-scanner

// camera-samples/CameraX-MLKit, based on View System
// https://github.com/android/camera-samples/tree/main/CameraX-MLKit

@ExperimentalPermissionsApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QRCodeScannerTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
                        Button(
                            onClick = {
                                cameraPermissionState.launchPermissionRequest()
                            }
                        ) {
                            Text(text = "Camera Permission")
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        CameraPreview()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
