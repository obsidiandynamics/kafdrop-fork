/*
 * Copyright 2016 HomeAdvisor, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */

package com.homeadvisor.kafdrop.model;

import org.junit.*;

import static org.junit.Assert.*;

public class ConsumerPartitionVOTest {
  private void doLagTest(long first, long last, long offset, long expectedLag) {
    final ConsumerPartitionVO partition = new ConsumerPartitionVO("test", "test", 0);
    partition.setFirstOffset(first);
    partition.setSize(last);
    partition.setOffset(offset);
    assertEquals("Unexpected lag", expectedLag, partition.getLag());
  }

  @Test
  public void testGetLag() throws Exception {
    doLagTest(0, 0, 0, 0);
    doLagTest(-1, -1, -1, 0);
    doLagTest(5, 10, 8, 2);
    doLagTest(5, 10, 2, 5);
    doLagTest(6, 6, 2, 0);
    doLagTest(5, 10, -1, 5);
  }
}