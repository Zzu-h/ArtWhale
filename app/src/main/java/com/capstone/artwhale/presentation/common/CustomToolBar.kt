package com.capstone.artwhale.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.util.Base64
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.capstone.artwhale.R
import com.capstone.artwhale.databinding.ViewToolBarCustomBinding
import java.io.ByteArrayInputStream
import java.io.ObjectInputStream

class CustomToolBar(
    context: Context,
    attrs: AttributeSet?
) : ConstraintLayout(context, attrs) {

    private val binding: ViewToolBarCustomBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.view_tool_bar_custom,
            this,
            true
        )
    private val currentMode: Int

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomToolBar)
        currentMode = a.getInt(R.styleable.CustomToolBar_toolBarType, 0)

        with(binding) {
            if (currentMode != 0x00)
                setAppBarTitle(a.getString(R.styleable.CustomToolBar_toolbarTitle).toString())

            a.getDrawable(R.styleable.CustomToolBar_setDefaultIcon)?.apply {
                ivDefault.setImageDrawable(this)
                ivDefault.setOnClickListener {
                    val callBackStr = a.getString(R.styleable.CustomToolBar_onClickDefaultIcon)
                        ?: return@setOnClickListener
                    createCallBackFunc(callBackStr)()
                }
            }

            a.getDrawable(R.styleable.CustomToolBar_setAdditionalIcon)?.apply {
                ivAdditional.isVisible = true
                ivAdditional.setImageDrawable(this)
                ivAdditional.setOnClickListener {
                    val callBackStr = a.getString(R.styleable.CustomToolBar_onClickAdditionalIcon)
                        ?: return@setOnClickListener
                    createCallBackFunc(callBackStr)()
                }
            }
        }
        a.recycle()
    }

    fun setOnClickDefaultIcon(c: () -> Unit) = binding.ivDefault.setOnClickListener { c() }
    fun setOnClickAdditionalIcon(c: () -> Unit) = binding.ivAdditional.setOnClickListener { c() }

    fun setAppBarTitle(title: String) {
        with(binding) {
            tvTitle.isVisible = true
            tvTitle.text = title
            ivTitle.isGone = true
        }
    }

    private fun createCallBackFunc(callBackStr: String): () -> Unit {
        val decoded = Base64.decode(callBackStr, Base64.DEFAULT)

        return ObjectInputStream(ByteArrayInputStream(decoded))
            .readObject() as (() -> Unit)
    }
}