package com.javacreed.api.gossip;

@FunctionalInterface
public interface GossipListenerRegistration {

  void unregister();
}
