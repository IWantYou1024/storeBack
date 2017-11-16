package cn.itcast.service.test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.dao.cargo.ContractProductDao;
import cn.itcast.domain.cargo.Contract;
import cn.itcast.domain.cargo.ContractProduct;
import cn.itcast.service.ContractProductService;
import cn.itcast.service.ContractService;
import cn.itcast.utils.UtilFuns;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ContractProductServiceTest {

	@Autowired
	private ContractProductService contractProductService;

	@Autowired
	private ContractService contractService;

	@Autowired
	private ContractProductDao contractProductDao;

	@Test
	// 合同按月份查询测试
	public void test01() throws Exception {

		DateFormat df = new SimpleDateFormat("yyyy-MM");
		Date parse = df.parse("2017-01");
		final Timestamp ts1 = new Timestamp(parse.getTime());

		parse.setMonth(parse.getMonth() + 1);
		final Timestamp ts2 = new Timestamp(parse.getTime());

		Specification<Contract> spec = new Specification<Contract>() {
			public Predicate toPredicate(Root<Contract> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Order asc = cb.asc(root.get("shipTime"));
				query.orderBy(asc);

				Predicate between = cb.between(root.get("shipTime").as(Timestamp.class), ts1, ts2);
				query.where(between);

				return query.getRestriction();
			}
		};

		List<Contract> list = contractService.find(spec);

		if (list.size() > 0) {
			System.out.println(list.size());
			for (Contract contract : list) {
				System.out.println(contract.getId() + "@@" + contract.getCustomName() + "##" + contract.getShipTime());
			}
		}
	}

	@Test
	// 货物按月份查询测试
	public void test04() throws Exception {

		DateFormat df = new SimpleDateFormat("yyyy-MM");
		Date parse = df.parse("2017-02");
		final Timestamp ts1 = new Timestamp(parse.getTime());

		parse.setMonth(parse.getMonth() + 1);
		final Timestamp ts2 = new Timestamp(parse.getTime());

		Specification<ContractProduct> spec = new Specification<ContractProduct>() {
			public Predicate toPredicate(Root<ContractProduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<ContractProduct, Contract> join = root.join("contract");
				Predicate between = cb.between(join.get("shipTime").as(Timestamp.class), ts1, ts2);
				return between;
			}
		};

		List<ContractProduct> list = contractProductService.find(spec);

		if (list.size() > 0) {
			System.out.println(list.size());
			for (ContractProduct contractProduct : list) {
				System.out.println(contractProduct.getId() + "@@" + contractProduct.getFactoryName() + "##"
						+ contractProduct.getContract().getShipTime());
			}
		}
	}

	@Test
	public void test02() throws Exception {
		Contract contract = contractService.get("8a7e81015acf673a015acf6dc97e0000");
		Date shipTime = contract.getShipTime();
		System.out.println(shipTime + "@@" + shipTime.getClass());
	}

	@Test
	public void test03() {
		// Dept dept = contractProductService.get(Dept.class, "100");
		// System.out.println(dept);
	}

	@Test
	public void test05() throws Exception {
		List<ContractProduct> list = contractProductDao.findByShipTime("2015-07");
		for (ContractProduct cp : list) {
			System.out.println(cp.getFactoryName() + "@@" + cp.getContract().getShipTime());
		}
	}

	@Test
	public void test08() throws Exception {
		// List<ContractProduct> list =
		// contractProductDao.findByTime("2017-02");
		// for (ContractProduct cp : list) {
		// System.out.println(cp.getFactoryName() + "@@" +
		// cp.getContract().getShipTime());
		// }
	}

	@Test
	public void test06() {
		String ids = "4028a9bd515b2e8201515b3339860000";
		String ids2 = "402895c2515129200151514376b80002";

		String[] strArr = { ids, ids2 };

		String joinInStr = UtilFuns.joinInStr(strArr);

		List<ContractProduct> cpList = contractProductDao.findAllByContractIds(joinInStr);
		for (ContractProduct cp : cpList) {
			System.out.println(cp.getFactoryName() + "@@" + cp.getId() + "##" + cp.getContract().getCustomName());
		}
	}

	@Test
	public void test07() {
		String ids = "4028a9bd515b2e8201515b3339860000";
		String ids2 = "402895c2515129200151514376b80002";
		final String ids3 = ids + ", " + ids2;

		final Object[] split = ids3.split(", ");

		Specification<ContractProduct> spec = new Specification<ContractProduct>() {
			public Predicate toPredicate(Root<ContractProduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Expression<String> as = root.join("contract").get("id").as(String.class);
				Predicate in = as.in(split);
				return in;
			}
		};
		List<ContractProduct> cpList = contractProductDao.findAll(spec);

		System.out.println("specification条件查询");
		for (ContractProduct cp : cpList) {
			System.out.println(cp.getFactoryName() + "@@" + cp.getId() + "##" + cp.getContract().getCustomName());
		}
	}

}
