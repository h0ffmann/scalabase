/*
 * Copyright 2019 Hoffmann
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

package config

import scala.concurrent.Await
import scala.concurrent.Future
import scala.concurrent.duration.Duration

import cats.effect.ContextShift
import cats.effect.IO
import ciris.ConfigException
import config.akka.SeedNodesConfig._
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.must.Matchers

class HostConfigSpec extends AsyncFlatSpec with Matchers {

  private implicit val cs: ContextShift[IO] = IO.contextShift(executionContext)

  def noFuture[T](f: Future[T]): T = Await.result(f, Duration.Inf)

  val badIp   = "10.233.244.333"
  val goodIp  = "10.255.254.10"
  val badUrl  = "google.com"
  val goodUrl = "http://google.com"

  it should "Test non defined" in {
    assertThrows[ConfigException](noFuture(seedNodesConfig().load[IO].unsafeToFuture()))
  }
//
//  it should "Test defined buy empty" in {
//    Env.setEnv("SEED_NODES", "")
//    assertThrows[ConfigException](noFuture(seedNodesConfig().load[IO].unsafeToFuture()))
//  }
//
//  it should "Test bad ip" in {
//    Env.setEnv("SEED_NODES", badIp)
//    assertThrows[ConfigException](noFuture(seedNodesConfig().load[IO].unsafeToFuture()))
//  }
//
//  it should "Test single correct" in {
//    Env.setEnv("SEED_NODES", goodIp)
//    assert(noFuture(seedNodesConfig().load[IO].unsafeToFuture()).hosts == List(goodIp))
//  }
//
//  it should "Test list correct" in {
//    Env.setEnv("SEED_NODES", s"$goodIp, $goodUrl")
//    assert(noFuture(seedNodesConfig().load[IO].unsafeToFuture()).hosts == List(goodIp, goodUrl))
//  }
//
//  it should "Test at least one incorrect" in {
//    Env.setEnv("SEED_NODES", s"$goodIp, $badUrl")
//    assertThrows[ConfigException](noFuture(seedNodesConfig().load[IO].unsafeToFuture()))
//  }
}
