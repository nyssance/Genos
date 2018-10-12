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

package genos.models;

import android.content.Context;

import androidx.annotation.NonNull;
import genos.Helper;

abstract class BaseItem {
    @NonNull
    public String name;
    @NonNull
    public String title;

    public BaseItem(@NonNull String name, @NonNull Context context) {
        int resId = Helper.getResId(context, name, "string");
        this.name = name;
        this.title = resId == 0 ? name : context.getString(resId);
        // this(name, name);
    }

    public BaseItem(@NonNull String name, @NonNull String title) {
        this.name = name;
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
