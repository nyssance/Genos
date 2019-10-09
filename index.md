---
layout: default
---
[![Download](https://api.bintray.com/packages/nyssance/maven/genos/images/download.svg)](https://bintray.com/nyssance/maven/genos/_latestVersion)

* TOC
{:toc}

## Show you the code

### __Step 0 (About 10 minutes to 1 day):__

Install Java & [Android Studio 3.6](https://developer.android.com/studio/).

### __Step 1 (2 minutes):__

Create New project. [official guide][10]

Screen | Configure
------ | ---------
Select a Project Template | Empty Activity
Configure Your Project | Minimum SDK: API 21

Open the _build.gradle (Module: app)_ file for your app module, add Genos to the `dependencies` section.
```gradle
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    // Replace default by Genos
    // implementation"org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    // implementation 'androidx.appcompat:appcompat:1.0.2'
    // implementation 'androidx.core:core-ktx:1.0.2'
    // implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'com.nyssance.genos:genos:1.2.0'
    ...
```

### __Step 2 (5 minutes):__

Create 4 classes: _User_, _APIService_, _Global_, _UserList_

_User_

```kotlin
import com.google.gson.annotations.SerializedName

data class User(
        val id: Long,
        @SerializedName("login") val username: String,
        @SerializedName("avatar_url") val avatarUrl: String
)
```

_APIService_
```kotlin
interface APIService {
    @GET("repos/square/retrofit/contributors")
    fun userList(@Query("page") page: Int): Call<List<User>>

    @GET("users/{username}")
    fun userDetail(@Path("username") username: String): Call<User>
}
```

_Global_
```kotlin
import genos.Global

lateinit var API: APIService

fun config() {
    ...
    API = Global.retrofit("https://api.github.com").create(APIService::class.java)
}
```

_UserList_
```kotlin
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
        Snackbar.make(listView, "Replace with your own action", Snackbar.LENGTH_SHORT).show()
    }
}
```

### __Step 3 (1 minutes)__

Modify _MainActivity_, _AndroidManifest.xml_

```kotlin
import genos.ui.activity.TabBarActivity
import genos.ui.fragment.PlaceholderFragment

class MainActivity : TabBarActivity(1) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        config()
        supportActionBar?.setTitle(R.string.app_name)
        with(fragments) {
            append(R.id.navigation_home, UserList())
            append(R.id.navigation_discover, PlaceholderFragment.instance("2"))
            append(R.id.navigation_me, PlaceholderFragment.instance("3"))
        }
    }
}
```

_AndroidManifest.xml_
```xml
    <uses-permission android:name="android.permission.INTERNET" />

    <application
        ...
        android:theme="@style/Theme.Genos">
        ...
    </application>

</manifest>
```

### __Step 4__

Run it
__Congratulations! your are an Android expert~~__

## Vendor

- Android
  - [Android Jetpack](https://developer.android.com/jetpack/)
- Others
  - [Retrofit](https://square.github.io/retrofit/)
  - [Glide](https://github.com/bumptech/glide)
  - [EventBus](https://github.com/greenrobot/EventBus)
  - [Logger](https://github.com/orhanobut/logger)

Special thanks [bintray-release](https://github.com/novoda/bintray-release), you save my life.

## License

    Copyright 2018 NY <nyssance@icloud.com>

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        https://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[2]: https://search.maven.org/remote_content?g=com.nyssance.genos&a=genos&v=LATEST
[10]: https://developer.android.com/studio/projects/create-project
