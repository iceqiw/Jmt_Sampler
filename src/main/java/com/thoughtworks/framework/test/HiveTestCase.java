package com.thoughtworks.framework.test;

import com.thoughtworks.framework.utils.HiveMetaStoreClientWrapper;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.thrift.TException;

public class HiveTestCase {


  private static HiveMetaStoreClient client;
  private static HiveMetaStoreClientWrapper wrapper;


  public static void main(String[] args) throws TException {
    wrapper = HiveMetaStoreClientWrapper.builder().url("thrift://127.0.0.1:9083").build();
    wrapper.init();
    client = wrapper.getNewClient();
    client.getAllDatabases().forEach(System.out::println);
  }

}
