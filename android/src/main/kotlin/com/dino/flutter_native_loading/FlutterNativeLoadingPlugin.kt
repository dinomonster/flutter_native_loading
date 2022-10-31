package com.dino.flutter_native_loading

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.util.Log
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** FlutterNativeLoadingPlugin */
class FlutterNativeLoadingPlugin : FlutterPlugin, MethodCallHandler, ActivityAware {
    private var activity: Activity? = null
    var progressDialog: Dialog? = null

    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel


    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter_native_loading")
        channel.setMethodCallHandler(this)
    }



    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method.equals("showLoading")) {
            showLoading()
        } else if (call.method.equals("hideLoading")) {
            Log.i("HideLoading", "true")
            progressDialog?.dismiss()
        } else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    fun showLoading() {
        activity?.let {
            progressDialog = Dialog(activity!!, R.style.loading_dialog_progress_style)
            progressDialog?.apply {
                setCancelable(false)
                setContentView(R.layout.progress_bar)
                window?.setBackgroundDrawableResource(android.R.color.transparent)
                window?.setWindowAnimations(R.style.loading_dialog_anim_style)
                show()
            }
        }

    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        activity = binding.activity
    }

    override fun onDetachedFromActivityForConfigChanges() {
        TODO("Not yet implemented")
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        TODO("Not yet implemented")
    }

    override fun onDetachedFromActivity() {
        TODO("Not yet implemented")
    }
}
