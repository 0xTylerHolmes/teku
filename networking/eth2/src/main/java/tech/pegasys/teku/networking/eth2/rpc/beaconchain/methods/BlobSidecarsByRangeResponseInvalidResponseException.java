/*
 * Copyright Consensys Software Inc., 2022
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package tech.pegasys.teku.networking.eth2.rpc.beaconchain.methods;

import tech.pegasys.teku.networking.eth2.rpc.core.InvalidResponseException;
import tech.pegasys.teku.networking.p2p.peer.Peer;

public class BlobSidecarsByRangeResponseInvalidResponseException extends InvalidResponseException {

  public BlobSidecarsByRangeResponseInvalidResponseException(
      Peer peer, InvalidResponseType invalidResponseType) {
    super(
        String.format(
            "Received invalid response from peer %s: %s", peer, invalidResponseType.describe()));
  }

  public BlobSidecarsByRangeResponseInvalidResponseException(
      InvalidResponseType invalidResponseType) {
    super("Received invalid response: " + invalidResponseType.describe());
  }

  public enum InvalidResponseType {
    BLOB_SIDECAR_KZG_VERIFICATION_FAILED("KZG verification for BlobSidecar is failed"),
    BLOB_SIDECAR_SLOT_NOT_IN_RANGE("BlobSidecar slot not in requested range"),
    BLOB_SIDECAR_UNEXPECTED_INDEX("BlobSidecar with unexpected index"),
    BLOB_SIDECAR_UNKNOWN_PARENT(
        "BlobSidecar parent blockRoot doesn't match previous blobSidecar blockRoot"),
    BLOB_SIDECAR_UNEXPECTED_SLOT("BlobSidecar slot is from block with incorrect slot");

    private final String description;

    InvalidResponseType(String description) {
      this.description = description;
    }

    public String describe() {
      return description;
    }
  }
}
