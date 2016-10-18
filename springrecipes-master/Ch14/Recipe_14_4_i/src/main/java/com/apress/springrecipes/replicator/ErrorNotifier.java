package com.apress.springrecipes.replicator;

public interface ErrorNotifier {

    public void notifyCopyError(String srcDir, String destDir, String filename);
}