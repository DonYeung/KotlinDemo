package com.shuixin.buildsrc

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import com.shuixin.asm.LifecycleClassVisitor
import groovy.io.FileType
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter

public class LifeCycleTransform extends Transform {
    @Override
    String getName() {
        return "LifeCycleTransform"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.PROJECT_ONLY
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
//        super.transform(transformInvocation)
        Collection<TransformInput> transformInputs = transformInvocation.inputs
        TransformOutputProvider outputProvider = transformInvocation.outputProvider

        transformInputs.each {TransformInput transformInput->
            transformInput .directoryInputs.each { DirectoryInput directoryInput ->
                File dir = directoryInput.file
                if (dir){
//                    dir.traverse(type: FileType.FILES,nameFilter: -/.*\.classs/){File file->
                      dir.traverse(type: FileType.FILES, nameFilter: ~/.*\.class/) { File file ->

                        println "find class :${file.name}"

                          ClassReader classReader = new ClassReader(file.bytes)
                          ClassWriter classWriter = new ClassWriter(classReader,ClassWriter.COMPUTE_MAXS)
                          ClassVisitor classVisitor = new LifecycleClassVisitor(classWriter)
                          classReader.accept(classVisitor,ClassReader.EXPAND_FRAMES)
                          byte [] bytes = classWriter.toByteArray()
                          FileOutputStream outputStream = new FileOutputStream(file.path)
                          outputStream.write(bytes)
                          outputStream.close()
                    }
                }

                def dest = outputProvider.getContentLocation(directoryInput.name,directoryInput.contentTypes,directoryInput.scopes, Format.DIRECTORY)
                FileUtils.copyDirectory(directoryInput.file,dest)
            }
        }
    }
}
