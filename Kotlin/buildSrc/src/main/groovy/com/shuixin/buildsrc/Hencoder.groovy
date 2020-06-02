package com.shuixin.buildsrc

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project;

class Hencoder implements Plugin<Project>{
    @Override
    void apply(Project target) {
        def extension = target.extensions.create("hencoder",HenCoderExtension)
        target.afterEvaluate {
            println "Hi ${extension.name}"
        }

        /*def transform = new HencoderTransform()
        def baseExtension = target.extensions.getByType(BaseExtension)
        baseExtension.registerTransform(transform)*/
        def transform = new LifeCycleTransform()
        def baseExtension = target.extensions.getByType(BaseExtension)
        baseExtension.registerTransform(transform)
    }
}