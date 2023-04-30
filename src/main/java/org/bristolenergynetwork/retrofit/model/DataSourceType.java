package org.bristolenergynetwork.retrofit.model;

import org.bristolenergynetwork.retrofit.datasource.DataSource;
import org.bristolenergynetwork.retrofit.datasource.GreenRegister;
import org.bristolenergynetwork.retrofit.datasource.MCSHttps;
import org.bristolenergynetwork.retrofit.datasource.TestSource;
import org.bristolenergynetwork.retrofit.datasource.TrustMarkHttps;
import org.bristolenergynetwork.retrofit.datasource.VerifiedTradeHttps;

public enum DataSourceType {
  GREEN_REGISTER,
  TRUSTMARK,
  MCS,
  VERIFIED_TRADES,
  TEST;

  public DataSource create() {
    switch (this) {
      case GREEN_REGISTER:
        return new GreenRegister();
      case TRUSTMARK:
        return new TrustMarkHttps();
      case MCS:
        return new MCSHttps();
      case VERIFIED_TRADES:
        return new VerifiedTradeHttps();
      case TEST:
        return new TestSource();
      default:
        return null;
    }
  }
}
