package org.openpaas.ieda.hbdeploy.web.deploy.cfdeployment.dto;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Repository;
@Repository
public class HbCfDeploymentDefaultConfigDTO {
	private Integer id;
    @NotNull
    private String defaultConfigName; //  별칭
    @NotNull
    private String iaasType; // 클라우드 인프라 환경 타입
    private String cfDeploymentVersion;
    private String domain;
    private String domainOrganization;
    private String cfDbType;
    private String createUserId;//등록자 아이디
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDefaultConfigName() {
		return defaultConfigName;
	}
	public void setDefaultConfigName(String defaultConfigName) {
		this.defaultConfigName = defaultConfigName;
	}
	public String getIaasType() {
		return iaasType;
	}
	public void setIaasType(String iaasType) {
		this.iaasType = iaasType;
	}
	public String getCfDeploymentVersion() {
		return cfDeploymentVersion;
	}
	public void setCfDeploymentVersion(String cfDeploymentVersion) {
		this.cfDeploymentVersion = cfDeploymentVersion;
	}

	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getDomainOrganization() {
		return domainOrganization;
	}
	public void setDomainOrganization(String domainOrganization) {
		this.domainOrganization = domainOrganization;
	}

	public String getCfDbType() {
		return cfDbType;
	}
	public void setCfDbType(String cfDbType) {
		this.cfDbType = cfDbType;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
}