package njit.service;

import java.util.List;

import njit.model.Resource;

public interface ResourceService extends BaseService<Resource>{

	public void initPath(List<String> paths);

}
