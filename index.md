---
layout: default
---
[ ![Download](https://api.bintray.com/packages/nyssance/maven/genos/images/download.svg) ](https://bintray.com/nyssance/maven/genos/_latestVersion)

* TOC
{:toc}

### Show you the code
#### __Step 0 (between 10 minutes to 1 day):__

Install Java8 & [Android Studio 3.2.1](https://developer.android.com/studio/).

#### __Step 1 (need 2 minutes):__

Start a new project. [official guide][10]

Screen | Configure
------ | ---------
Target Android Devices | Phone and Tablet : API 21
Add an Activity to Mobile | Empty Activity

Config _Gradle Scripts: build.gradle (Module: app)_.
```gradle
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    // Replace default by Genos
    // implementation 'com.android.support:appcompat-v7:28.0.0'
    // implementation 'com.android.support.constraint:constraint-layout:1.1.4'
    implementation 'com.nyssance.genos:genos:1.1.4'
    ...
```

#### __Step 2 (5 minutes):__

Create 4 classes: _User_, _APIService_, _AppManager_, _UserList_

_User_

```kotlin
import com.google.gson.annotations.SerializedName

data class User(
    var login: String?,
    var id: Long,
    @SerializedName("avatar_url") var avatarUrl: String?,
    var name: String?
)
```

_APIService_
```kotlin
interface APIService {
    @GET("repos/square/retrofit/contributors")
    fun userList(@Query("page") page: Int): Call<List<User>>

    @GET("users/{login}")
    fun userDetail(@Path("login") login: String): Call<User>
}
```

_AppManager_
```kotlin
import genos.BaseAppManager

class AppManager private constructor() : BaseAppManager() {
    companion object {
        val instance = AppManager()
        lateinit var API: APIService
    }

    override fun settings() {
        BASE_URL = "https://api.github.com"
        // Create retrofit
        API = onCreateRetrofit().create(APIService::class.java)
    }
    ...

```

_UserList_
```
import genos.ui.fragment.TableList
import genos.ui.viewholder.SubtitleHolder

class UserList : TableList<User, SubtitleHolder>() {
    override fun onPrepare() {
        call = API.userList(page)
        tileId = R.layout.list_item_subtitle
    }

    override fun onDisplayItem(item: User, holder: SubtitleHolder, viewType: Int) {
        holder.title.text = item.login
        holder.subtitle.text = item.id.toString()
        item.avatarUrl?.let {
            holder.setImage(holder.icon, it)
        }
    }

    override fun onOpenItem(item: User) {
        Snackbar.make(listView, ""Replace with your own action"", Snackbar.LENGTH_SHORT).show()
    }
}
```

#### __Step 3 (1 minutes)__

Modify _MainActivity_, _AndroidManifest.xml_
```kotlin
import genos.ui.TabBarActivity

class MainActivity : TabBarActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(fragments) {
            append(R.id.navigation_home, UserList())
            append(R.id.navigation_discover, PlaceholderFragment.newInstance(2))
            append(R.id.navigation_me, PlaceholderFragment.newInstance(3))
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

#### __Step 4__

Run it
__Congratulations! your are an Android expert~~__

### Vendor
- Android
  - [Android Jetpack](https://developer.android.com/jetpack/)
- Others
  - [Retrofit](https://square.github.io/retrofit/)
  - [Glide](https://github.com/bumptech/glide)
  - [EventBus](https://github.com/greenrobot/EventBus)
  - [Logger](https://github.com/orhanobut/logger)

Special thanks [bintray-release](https://github.com/novoda/bintray-release), you save my life.

### License

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
[10]: https://developer.android.com/studio/projects/create-project.html
