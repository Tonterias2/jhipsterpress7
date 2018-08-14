package web.jhp7.web.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Blockuser entity.
 */
public class BlockuserDTO implements Serializable {

    private Long id;

    private Instant creationDate;

    private Long cblockeduserId;

    private Long cblockinguserId;

    private Long blockeduserId;

    private Long blockinguserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Long getCblockeduserId() {
        return cblockeduserId;
    }

    public void setCblockeduserId(Long communityId) {
        this.cblockeduserId = communityId;
    }

    public Long getCblockinguserId() {
        return cblockinguserId;
    }

    public void setCblockinguserId(Long communityId) {
        this.cblockinguserId = communityId;
    }

    public Long getBlockeduserId() {
        return blockeduserId;
    }

    public void setBlockeduserId(Long profileId) {
        this.blockeduserId = profileId;
    }

    public Long getBlockinguserId() {
        return blockinguserId;
    }

    public void setBlockinguserId(Long profileId) {
        this.blockinguserId = profileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BlockuserDTO blockuserDTO = (BlockuserDTO) o;
        if (blockuserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), blockuserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BlockuserDTO{" +
            "id=" + getId() +
            ", creationDate='" + getCreationDate() + "'" +
            ", cblockeduser=" + getCblockeduserId() +
            ", cblockinguser=" + getCblockinguserId() +
            ", blockeduser=" + getBlockeduserId() +
            ", blockinguser=" + getBlockinguserId() +
            "}";
    }
}
