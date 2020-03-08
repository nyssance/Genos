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
import androidx.compose.Composable
import androidx.ui.core.Text
import com.example.genos.API
import com.example.genos.R
import com.example.genos.model.User
import genos.ui.activity.CollapsingActivity
import genos.ui.fragment.generic.Detail

class UserDetail : Detail<User>() {
    override fun onCreate(intent: Intent, id: String) {
        call = API.userDetail(id)
    }

    @Composable
    override fun onCompose() {
        UserDetail(getString(R.string.large_text))
    }

    override fun onDisplay(data: User) {
        (requireActivity() as UserDetailActivity).collapsingToolbar?.title = data.username
    }
}

class UserDetailActivity : CollapsingActivity() {
    override fun onCreateFragment() = UserDetail()
}

@Composable
fun UserDetail(name: String) {
    Text(name)
}
