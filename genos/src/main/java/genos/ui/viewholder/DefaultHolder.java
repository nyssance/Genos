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

package genos.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class DefaultHolder extends BaseHolder {
    @NonNull
    public ImageView icon;
    @NonNull
    public TextView title;

    public DefaultHolder(@NonNull View itemView) {
        super(itemView);
        icon = getView(android.R.id.icon);
        title = getView(android.R.id.title);
    }
}
