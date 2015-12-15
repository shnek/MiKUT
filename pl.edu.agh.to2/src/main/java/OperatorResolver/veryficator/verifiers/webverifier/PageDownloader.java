package OperatorResolver.veryficator.verifiers.webverifier;

import java.net.MalformedURLException;

public interface PageDownloader {
    String getLine(String stringUrl)throws MalformedURLException;
}
