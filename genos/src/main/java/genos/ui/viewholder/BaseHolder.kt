/*
 * Copyright 2018 NY <nyssance@icloud.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package genos.ui.viewholder

import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.core.util.getOrElse
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orhanobut.logger.Logger

open class BaseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val views = SparseArray<View>()

    fun <V : View> getView(@IdRes id: Int): V {
        return views.getOrElse(id) {
            val view = itemView.findViewById<V>(id)
            view?.let {
                views.put(id, it)
            } ?: run {
                Logger.t("viewholder").e("itemView.findViewById return null. check your tile id, IdRes: ${itemView.context.resources.getResourceName(id)}")
            }
            view
        } as V
    }

    fun setText(@IdRes id: Int, text: CharSequence) {
        getView<TextView>(id).text = text
    }

    fun setImage(@IdRes id: Int, string: String) {
        setImage(getView<ImageView>(id), string)
    }

    fun setImage(imageView: ImageView, string: String) {
        Glide.with(itemView.context).load(string).into(imageView)
    }
}