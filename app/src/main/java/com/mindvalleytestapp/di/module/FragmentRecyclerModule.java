/*
 * Copyright (c) 2016 Filippo Engidashet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mindvalleytestapp.di.module;

import com.mindvalleytestapp.api.UsersApiService;
import com.mindvalleytestapp.di.scope.PerFragment;
import com.mindvalleytestapp.mvp.view.RecyclerLoaderView;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class FragmentRecyclerModule {

    private RecyclerLoaderView mRecyclerView;

    public FragmentRecyclerModule(RecyclerLoaderView mView) {
        this.mRecyclerView = mView;
    }

    @Provides
    @PerFragment
    protected UsersApiService provideUsersApiService(Retrofit retrofit) {
        return retrofit.create(UsersApiService.class);
    }


    @PerFragment
    @Provides
    protected RecyclerLoaderView provideRecyclerLoaderView() {
        return mRecyclerView;
    }

}