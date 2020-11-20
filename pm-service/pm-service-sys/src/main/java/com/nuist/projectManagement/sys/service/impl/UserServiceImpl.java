package com.pactera.esc.sys.service.impl;

import com.pactera.esc.core.common.BaseErrorCode;
import com.pactera.esc.core.common.Constants;
import com.pactera.esc.core.dao.RoleRepository;
import com.pactera.esc.core.dao.UserRepository;
import com.pactera.esc.core.domain.Organization;
import com.pactera.esc.core.domain.Role;
import com.pactera.esc.core.domain.User;
import com.pactera.esc.core.dto.UserDTO;
import com.pactera.esc.core.dto.mapper.UserMapper;
import com.pactera.esc.core.exception.BaseException;
import com.pactera.esc.core.exception.BusinessException;
import com.pactera.esc.core.exception.ItemNotFoundException;
import com.pactera.esc.core.util.BeanUtils;
import com.pactera.esc.core.util.CommonUtil;
import com.pactera.esc.sys.service.OrganizationService;
import com.pactera.esc.sys.service.RoleService;
import com.pactera.esc.sys.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author si.chen
 * @date 2020/3/26 17:00
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private RoleRepository roleRepository;
    private RoleService roleService;
    @javax.annotation.Resource
    private OrganizationService organizationService;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository,
                         RoleService roleService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
    }

    @Override
    public User get(String id) throws BaseException {
        return userRepository.findById(id).orElseThrow(() -> new BaseException(BaseErrorCode.ITEM_NOT_FOUND));
    }

    @Override
    public UserDTO detail(String id) throws BaseException {
        User user = this.get(id);
//        UserRole param = new UserRole();
//        param.setUser(user);
//        UserDTO userDTO = userMapper.toDTO(user);
//        List<String> list = userRoleService.list(param).stream().map(UserRoleDTO::getRoleId).collect(Collectors.toList());
//        userDTO.setRoleIds(list);
//        if (Objects.nonNull(user.getOrganization())) {
//            userDTO.setRoles(new HashSet<>(roleService.getRolesByOrg(user.getOrganization().getId())));
//        }
        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO find(User entity) throws BaseException {
        return userMapper.toDTO(userRepository.findOne(this.getSpecification(entity,null)).orElse(null));
    }

    @Override
    public List<UserDTO> list(User entity) throws BaseException {
        return userRepository.findAll(this.getSpecification(entity,null), Sort.by(entity.getSortParam().getDirection(),
            entity.getSortParam().getField()))
            .stream().map(user -> {
                UserDTO userDTO = userMapper.toDTO(user);
                userDTO.setRoleNames(getRoles(user).stream().map(roleDTO -> roleDTO.getName()).collect(Collectors.joining(",")));
                return userDTO;
            }).collect(Collectors.toList());
    }

    @Override
    @SuppressWarnings("Duplicates")
    public Page<UserDTO> page(User entity,User loginUser) throws BaseException {
        //page模糊查询
        entity.setUsernameLike(entity.getUsername());
        entity.setUsername(null);
        entity.setPhoneLike(entity.getPhone());
        entity.setPhone(null);
        entity.setType(entity.getType());
        Pageable pageable = PageRequest.of(entity.getPageable().getPageNo(), entity.getPageable().getPageSize(),
            Sort.by(entity.getSortParam().getDirection(), entity.getSortParam().getField()));
        return userRepository.findAll(this.getSpecification(entity,loginUser), pageable).map(user -> {
            UserDTO userDTO = userMapper.toDTO(user);
            userDTO.setRoleNames(getRoles(user).stream().map(roleDTO -> roleDTO.getName()).collect(Collectors.joining(",")));
            return userDTO;
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(UserDTO userDTO) throws BaseException {
        this.checkUniqueForCreate(userDTO);
        User user = userMapper.toEntity(userDTO);
        user.setRoles(userDTO.getRoleIds().stream().map(roleId->{
            Role role = new Role();
            role.setId(roleId);
            return role;
        }).collect(Collectors.toList()));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setEnable(Constants.ENABLE);
        userRepository.save(user);
//        if (userDTO.getType() == 1) {
//            userDTO.getRoleIds().forEach(roleId -> userRoleService.create(new UserRole(user, roleRepository.getOne(roleId))));
//        }
//        if (userDTO.getType() == 2) {
//            userRoleService.create(new UserRole(user, roleRepository.getOne("2")));
//        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserDTO userDTO) throws BaseException {
        User exist = this.get(userDTO.getId());
        this.checkUniqueForUpdate(userDTO);
        BeanUtils.copyNotNullProperties(userMapper.toEntity(userDTO), exist);
        exist.setPhone(userDTO.getPhone());
        exist.setEmail(userDTO.getEmail());
        exist.setAddress(userDTO.getAddress());
        if (userDTO.getRoleIds()!=null) {
            exist.setRoles(userDTO.getRoleIds().stream().map(roleId -> {
                Role role = new Role();
                role.setId(roleId);
                return role;
            }).collect(Collectors.toList()));
        }
        if (StringUtils.isNotBlank(userDTO.getNewPassword())) {
            exist.setPassword(new BCryptPasswordEncoder().encode(userDTO.getNewPassword()));
        }
        userRepository.save(exist);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws BaseException {
        User entity = this.get(id);
        //删除用户角色
        userRepository.delete(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User updatePassword(User user) throws BaseException {
        User entity = this.get(user.getId());
        //校验旧密码
        if (!this.checkPassword(user.getOldPassword(), entity.getPassword())) {
            throw new BusinessException(BaseErrorCode.PASSWORD_ERROR);
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        entity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User resetPassword(String id) throws BaseException {
        User user = this.get(id);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(Constants.DEFAULT_PASSWORD));
        return userRepository.save(user);
    }

    @Override
    public UserDTO currentUser(User user) {
        UserDTO dto = userMapper.toDTO(user);
        dto.setResources(roleService.getResourceTree(user));
        return dto;
    }

    @Override
    public Set<Role> getRoles(User user) {
//        Set<Role> roles = userRoleRepository.findByUserId(user.getId())
//            .stream()
//            .map(UserRole::getRole)
//            .collect(Collectors.toSet());
//        roles.forEach(role -> {
//            Set<Resource> resources = roleResourceRepository.findByRole(role)
//                .stream()
//                .map(RoleResource::getResource)
//                .collect(Collectors.toSet());
//            role.setResources(resources);
//        });
        return user.getRoles().stream().collect(Collectors.toSet());
    }

    @Override
    public UserDTO login(UserDTO user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return userMapper.toDTO(userRepository.findUserByUsername(user.getUsername())
            .filter(u -> bCryptPasswordEncoder.matches(user.getPassword(), u.getPassword()))
            .orElseThrow(() -> new ItemNotFoundException("Username or password error")));
    }

    /**
     * 校验唯一约束:新增
     */
    @SuppressWarnings("Duplicates")
    private void checkUniqueForCreate(UserDTO entity) throws BaseException {
        if (StringUtils.isNotBlank(entity.getUsername())) {
            User param = new User();
            param.setUsername(entity.getUsername());
            if (CommonUtil.isNotEmptyList(this.list(param))) {
                throw new BusinessException(BaseErrorCode.NAME_IS_USED);
            }
        }
    }

    /**
     * 校验唯一约束:修改
     */
    @SuppressWarnings("Duplicates")
    private void checkUniqueForUpdate(UserDTO entity) throws BaseException {
        if (StringUtils.isNotBlank(entity.getUsername())) {
            User param = new User();
            param.setUsername(entity.getUsername());
            param.setIdNe(entity.getId());
            if (CommonUtil.isNotEmptyList(this.list(param))) {
                throw new BusinessException(BaseErrorCode.NAME_IS_USED);
            }
        }
    }

    /**
     * 判断密码是否正确
     *
     * @param rawPassword     明文
     * @param encodedPassword 密文
     * @return boolean
     */
    private boolean checkPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }


    /**
     * 动态条件查询
     */
    @SuppressWarnings("Duplicates")
    private Specification<User> getSpecification(User entity,User loginUser) {
        return (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            if(!Objects.isNull(loginUser)){
                list.add(cb.notEqual(root.get("id"),loginUser.getId()));
                if(!Constants.ADMIN_USER_ID.equals(loginUser.getId())){
                    Optional.ofNullable(loginUser.getOrganization().getId()).ifPresent(id->{
                        List<String> ids = organizationService.getChildren(id).stream().map(Organization::getId).collect(Collectors.toList());
                        ids.add(id);
                        CriteriaBuilder.In<Object> in = cb.in(root.get("organization").get("id"));
                        ids.forEach(in::value);
                        list.add(in);
                    });
                }
            }
            //!=
            if (StringUtils.isNotBlank(entity.getIdNe())) {
                Predicate predicate = cb.notEqual(root.get("id"), entity.getIdNe());
                list.add(predicate);
            }
            //like
            if (StringUtils.isNotBlank(entity.getUsernameLike())) {
                Predicate predicate = cb.like(root.get("username"), "%" + entity.getUsernameLike() + "%");
                list.add(predicate);
            }
            //like
            if (StringUtils.isNotBlank(entity.getPhoneLike())) {
                Predicate predicate = cb.like(root.get("phone"), "%" + entity.getPhoneLike() + "%");
                list.add(predicate);
            }
            if (StringUtils.isNotBlank(entity.getUsername())) {
                Predicate predicate = cb.equal(root.get("username"), entity.getUsername());
                list.add(predicate);
            }
            if (entity.getOrganization() != null && StringUtils.isNotBlank(entity.getOrganization().getId())) {
                Predicate predicate = cb.equal(root.get("organization").get("id"), entity.getOrganization().getId());
                list.add(predicate);
            }
            if (entity.getType() != null) {
                Predicate predicate = cb.equal(root.get("type"), entity.getType());
                list.add(predicate);
            } else {
                Predicate predicate = cb.or(cb.equal(root.get("type"), 0), cb.equal(root.get("type"), 1));
                list.add(predicate);
            }
            query.where(list.toArray(new Predicate[0]));
            return query.getRestriction();
        };
    }
}
