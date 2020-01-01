package com.thoughtworks.framework.sampler;

import org.apache.jmeter.samplers.SampleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetaStoreSampler1 extends BaseSampler {

  private static final Logger LOGGER = LoggerFactory.getLogger(MetaStoreSampler1.class);

  @Override
  protected void runTestInternal(SampleResult result) throws Exception {
    LOGGER.info("run test");
    super.client.getAllDatabases().forEach(LOGGER::info);
  }
}
