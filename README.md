# QR Code Scanner
This Android app, named "QR Code Scanner", can detect QR codes with the help of CameraX and Google MLKit. Once a URL is detected, the info is added to a list of URLs and then the list instantly updates itself on the screen.

Jetpack Compose is used to build UI components of this app. Two main UI elements are populated on the screen: 

  1. Camera preview. 
  2. List showing URLs extracted from QR codes. On detection, the list adds an item on the top of it.

# Detected QR code using Android tablet (OS version 5.1)
This still image shows the app has detected the QR code on the flip side of a book from O'Reilly. As you can see, the display shows an opaque yellow square overlaid on the detected QR code. In addition, the app shows the decoded URL below it.

By tapping inside the yellow square overlaid on the QR code, you can move on to the web page pointed by the URL. This is a built-in feature of sample code [[2]](https://github.com/android/camera-samples/tree/main/CameraX-MLKit).

![preview image](./preview_image20231029.png)

# References 
The following articles and sample codes were greatly helpful to create this app. Thanks to all the contributors of the resources and CameraX, MLKit libraries. 

[[1] Jetpack compose Barcode/QR code scanner](https://www.codeplayon.com/jetpack-compose-barcode-qr-code-scanner/)


[[2] camera-samples/CameraX-MLKit](https://github.com/android/camera-samples/tree/main/CameraX-MLKit) 

[[3] CameraX 1.2 is now in Beta, 24 August 2022](https://android-developers.googleblog.com/2022/08/camerax-12-is-now-in-beta.html)

[[4] Catalin Ghita, Kickstart Modern Android Development with Jetpack and Kotlin](https://www.packtpub.com/product/kickstart-modern-android-development-with-jetpack-and-kotlin/9781801811071)

[[5] Neil Smyth, Jetpack Compose Essentials, eBookFrenzy](https://www.ebookfrenzy.com/pdf_previews/JetpackComposeEssentialsPreview.pdf)

# Features to be Added
1. Room Storage:
as the app loses a list of detected URLs once the app state goes to the background.
2. Navigation: when you tap an item of the URL list, you are unable to move on to the web page indicated by the item.

Oct. 29th, 2023





 






