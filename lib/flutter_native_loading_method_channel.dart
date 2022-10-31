import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'flutter_native_loading_platform_interface.dart';

/// An implementation of [FlutterNativeLoadingPlatform] that uses method channels.
class MethodChannelFlutterNativeLoading extends FlutterNativeLoadingPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('flutter_native_loading');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
