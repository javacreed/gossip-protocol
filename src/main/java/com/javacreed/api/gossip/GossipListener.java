package com.javacreed.api.gossip;

public interface GossipListener {

  void onHello(GossipConnection connection);

  void onGoodbye(GossipConnection connection);
}
