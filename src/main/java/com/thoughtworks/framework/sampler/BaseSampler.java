package com.thoughtworks.framework.sampler;

import com.thoughtworks.framework.utils.HiveMetaStoreClientWrapper;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public abstract class BaseSampler extends AbstractJavaSamplerClient implements Serializable {

  private static final Logger LOGGER = LoggerFactory.getLogger(BaseSampler.class);

  protected HiveMetaStoreClient client;
  protected HiveMetaStoreClientWrapper wrapper;
  private static final String URL = "url";


  @Override
  public void setupTest(JavaSamplerContext context) {
    try {
      LOGGER.info("setup start");
      String url = context.getParameter(URL);

      wrapper = HiveMetaStoreClientWrapper.builder().url(url).build();
      wrapper.init();
      client = wrapper.getNewClient();
      LOGGER.info("setup end");
    } catch (MetaException e) {
      e.printStackTrace();
    }

  }

  @Override
  public Arguments getDefaultParameters() {
    Arguments defaultParameters = new Arguments();
    defaultParameters.addArgument(URL, "thrift://127.0.0.1:9083");
    return defaultParameters;
  }

  @Override
  public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
    SampleResult result = new SampleResult();
    result.sampleStart();
    try {
      runTestInternal(result);
    } catch (Exception e) {
      result.setSuccessful(false);
    } finally {
      result.setSuccessful(true);

      result.sampleEnd();
    }
    return result;
  }

  protected abstract void runTestInternal(SampleResult result) throws Exception;
}
