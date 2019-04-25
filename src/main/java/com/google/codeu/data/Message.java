/*
 * Copyright 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.codeu.data;

import java.util.UUID;

/** A single message posted by a user. */
public class Message {
  private UUID id;
  private String user;
  private String text;
  private long timestamp;
  private float sentimentScore;
  private String location;
  private String imageUrl;

  /**
   * Constructs a new {@link Message} posted by {@code user} with {@code text} content. Generates a
   * random ID and uses the current system time for the creation time.
   */
  public Message(String user, String text, float sentimentScore, String location) {
    this(UUID.randomUUID(), user, text, System.currentTimeMillis(), sentimentScore, location);
  }

  public Message(UUID id, String user, String text, long timestamp, float sentimentScore, String location) {
    this.id = id;
    this.user = user;
    this.text = text;
    this.timestamp = timestamp;
    this.sentimentScore = sentimentScore;
    this.location = location;
  }

  //added by Hedaya
  public String getImageUrl(){
    return imageUrl;
  }

  public void setImageUrl(String setImage){
    imageUrl = setImage;
  }
  //end

  public UUID getId() {
    return id;
  }

  public String getUser() {
    return user;
  }

  public String getText() {
    return text;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public float getSentimentScore() {
    return sentimentScore;
  }

  public String getLocation() {
    return location;
  }
}
