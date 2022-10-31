import 'package:flutter_native_loading/flutter_native_loading.dart';
import 'package:flutter_native_loading/flutter_native_loading_method_channel.dart';
import 'package:flutter_native_loading/flutter_native_loading_platform_interface.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockFlutterNativeLoadingPlatform
    with MockPlatformInterfaceMixin
    implements FlutterNativeLoadingPlatform {
  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final FlutterNativeLoadingPlatform initialPlatform =
      FlutterNativeLoadingPlatform.instance;

  test('$MethodChannelFlutterNativeLoading is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelFlutterNativeLoading>());
  });

  test('getPlatformVersion', () async {
    FlutterNativeLoading flutterNativeLoadingPlugin = FlutterNativeLoading();
    MockFlutterNativeLoadingPlatform fakePlatform =
        MockFlutterNativeLoadingPlatform();
    FlutterNativeLoadingPlatform.instance = fakePlatform;
  });
}
