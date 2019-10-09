/*
 * Copyright 2018 NY <nyssance@icloud.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.genos.ui

import android.content.Intent
import com.example.genos.API
import com.example.genos.R
import com.example.genos.model.User
import genos.extension.navigate
import genos.extension.setImage
import genos.ui.fragment.generic.List
import genos.ui.viewholder.DefaultHolder

class UserList : List<User, DefaultHolder>() {
    override fun onCreate() {
        call = API.userList(page)  // A retrofit call of this fragment.
        tileId = R.layout.list_item_subtitle  // The layout res id of list item.
    }

    override fun onDisplayItem(item: User, view: DefaultHolder, viewType: Int) {
        with(view) {
            icon?.setImage(item.avatarUrl)
            title?.text = item.username
            subtitle?.text = item.id.toString()
        }
    }

    override fun onOpenItem(item: User) {
        val intent = Intent(requireContext(), UserDetailActivity::class.java).apply {
            putExtra("username", item.username)
        }
        navigate(intent)
    }
}
