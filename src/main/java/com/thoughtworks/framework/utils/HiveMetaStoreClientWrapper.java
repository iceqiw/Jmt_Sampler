package com.thoughtworks.framework.utils;

import lombok.Builder;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.MetaException;

@Builder
public class HiveMetaStoreClientWrapper {

  private String url;

  private static HiveMetaStoreClient instance;

  private final HiveConf conf = new HiveConf();

  public void init() {
    this.setConf();
  }

  private void setConf() {
    conf.set("hive.metastore.uris",url);
    this.security();
  }

  private void security() {

  }

  public HiveMetaStoreClient getSingleClient() throws MetaException {
    if (null == instance) {
      instance = new HiveMetaStoreClient(conf);
    }
    return instance;
  }

  public HiveMetaStoreClient getNewClient() throws MetaException {
    return new HiveMetaStoreClient(conf);
  }

}
