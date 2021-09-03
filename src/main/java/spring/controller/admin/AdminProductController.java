package spring.controller.admin;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.constant.GlobalConstant;
import spring.constant.URLConstant;
import spring.constant.ViewNameConstant;
import spring.model.ImageProduct;
import spring.model.Product;
import spring.model.User;
import spring.service.CategoryService;
import spring.service.ImageProductService;
import spring.service.ProductService;
import spring.service.ReviewsService;
import spring.util.FileUtil;
import spring.util.PageUtil;
import spring.util.StringUtil;
import spring.validate.ProductValidate;

@Controller
@RequestMapping(URLConstant.URL_ADMIN)
public class AdminProductController {
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ReviewsService reviewsService;

	@Autowired
	private ImageProductService imageProductService;

	@Autowired
	private ProductValidate productValidate;

	@GetMapping({ URLConstant.URL_ADMIN_PRODUCT_INDEX, URLConstant.URL_ADMIN_PRODUCT_INDEX_PAGINATION,
			URLConstant.URL_ADMIN_PRODUCT_SEARCH, URLConstant.URL_ADMIN_PRODUCT_SEARCH_PAGINATION })
	public String index(@PathVariable(required = false) Integer page, @RequestParam(required = false) String keyword,
			@PathVariable(required = false) String keywordURL, Model model, RedirectAttributes ra) {
		int currentPage = GlobalConstant.DEFAULT_PAGE;
		if (page != null) {
			if (page < GlobalConstant.DEFAULT_PAGE) {
				return "redirect:/" + URLConstant.URL_ADMIN_PRODUCT_INDEX_REDIRECT;
			}
			currentPage = page;
		}
		int offset = PageUtil.getOffset(currentPage);
		int totalRow = productService.totalRow();
		int totalPage = PageUtil.getTotalPage(totalRow);
		List<Product> listProduct = productService.getList(offset, GlobalConstant.TOTAL_ROW);
		if (keywordURL != null) {
			keyword = StringUtil.dashToSpace(keywordURL);
		}
		if (keyword != null) {
			if (keyword.equals(GlobalConstant.EMPTY)) {
				ra.addFlashAttribute("error", messageSource.getMessage("searchError", null, Locale.getDefault()));
				return "redirect:/" + URLConstant.URL_ADMIN_PRODUCT_INDEX_REDIRECT;
			}
			model.addAttribute("keyword", keyword);
			totalRow = productService.totalRowByName(keyword);
			totalPage = PageUtil.getTotalPage(totalRow);
			listProduct = productService.searchByName(keyword, offset, GlobalConstant.TOTAL_ROW);
		}
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalRow", totalRow);
		model.addAttribute("listProduct", listProduct);
		return ViewNameConstant.ADMIN_PRODUCT_INDEX;
	}

	@GetMapping(URLConstant.URL_ADMIN_PRODUCT_ADD)
	public String add(Model model) {
		model.addAttribute("listCat", categoryService.getAll());
		return ViewNameConstant.ADMIN_PRODUCT_ADD;
	}

	@Transactional
	@PostMapping(URLConstant.URL_ADMIN_PRODUCT_ADD)
	public String add(@Valid @ModelAttribute("productError") Product product, BindingResult productRs,
			@RequestParam("picture") List<MultipartFile> multipartFileList, Model model, RedirectAttributes ra,
			HttpServletRequest request) {
		model.addAttribute("listCat", categoryService.getAll());
		model.addAttribute("product", product);
		productValidate.validateCat(product.getCat().getId(), productRs);
		productValidate.validatePicture(model, multipartFileList);
		if (productRs.hasErrors()) {
			return ViewNameConstant.ADMIN_PRODUCT_ADD;
		}
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("adminUserLogin");
//		if (user == null) {
//			ra.addFlashAttribute("error", messageSource.getMessage("error", null, Locale.getDefault()));
//			return "redirect:/" + URLConstant.URL_ADMIN_PRODUCT_INDEX_REDIRECT;
//		}
		user = new User(1);
		product.setUser(user);
		if (productService.save(product) > 0) {
			Product newProduct = productService.getNewProduct();
			ImageProduct picture = new ImageProduct(newProduct);
			List<String> listFileName = FileUtil.uploadMultipleFile(multipartFileList, request,
					GlobalConstant.DIR_UPLOAD_PICTURE_PRODUCT);
			if (listFileName.size() > 0) {
				boolean check = true;
				for (String fileName : listFileName) {
					picture.setPicture(fileName);
					if (imageProductService.save(picture) > 0) {
						System.out.println();
					} else {
						check = false;
						FileUtil.delFile(fileName, request, GlobalConstant.DIR_UPLOAD_PICTURE_PRODUCT);
						ra.addFlashAttribute("error", messageSource.getMessage("error", null, Locale.getDefault()));
					}
				}
				if (check) {
					ra.addFlashAttribute("success",
							messageSource.getMessage("addProductSuccess", null, Locale.getDefault()));
				}
			}
		} else {
			ra.addFlashAttribute("error", messageSource.getMessage("error", null, Locale.getDefault()));
		}
		return "redirect:/" + URLConstant.URL_ADMIN_PRODUCT_INDEX_REDIRECT;
	}

	@GetMapping(URLConstant.URL_ADMIN_PRODUCT_DETAIL)
	public String detail(@PathVariable int id, Model model, RedirectAttributes ra) {
		Product product = productService.findById(id);
		if (product == null) {
			ra.addFlashAttribute("msg", messageSource.getMessage("noDataProduct", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_PRODUCT_INDEX_REDIRECT;
		}
		model.addAttribute("product", product);
		return ViewNameConstant.ADMIN_PRODUCT_DETAIL;
	}

	@GetMapping(URLConstant.URL_ADMIN_PRODUCT_PICTURE)
	public String picture(@PathVariable int id, Model model, RedirectAttributes ra) {
		Product product = productService.findById(id);
		List<ImageProduct> pictureList = imageProductService.findByProductId(id);
		if (product == null) {
			ra.addFlashAttribute("msg", messageSource.getMessage("noDataProduct", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_PRODUCT_INDEX_REDIRECT;
		}
		model.addAttribute("product", product);
		model.addAttribute("pictureList", pictureList);
		return ViewNameConstant.ADMIN_PRODUCT_PICTURE;
	}

	@PostMapping(URLConstant.URL_ADMIN_PRODUCT_PICTURE)
	public String addPicture(@ModelAttribute Product product,
			@RequestParam("picture") List<MultipartFile> multipartFileList, RedirectAttributes ra, Model model,
			HttpServletRequest request) {
		List<ImageProduct> pictureList = imageProductService.findByProductId(product.getId());
		model.addAttribute("pictureList", pictureList);
		model.addAttribute("product", product);
		if (productValidate.validatePicture(model, multipartFileList)) {
			return ViewNameConstant.ADMIN_PRODUCT_PICTURE;
		}
		List<String> listFileName = FileUtil.uploadMultipleFile(multipartFileList, request,
				GlobalConstant.DIR_UPLOAD_PICTURE_PRODUCT);
		ImageProduct picture = new ImageProduct(product);
		if (listFileName.size() > 0) {
			for (String fileName : listFileName) {
				picture.setPicture(fileName);
				if (imageProductService.save(picture) > 0) {
					ra.addFlashAttribute("success",
							messageSource.getMessage("addPictureSuccess", null, Locale.getDefault()));
				} else {
					FileUtil.delFile(fileName, request, GlobalConstant.DIR_UPLOAD_PICTURE_PRODUCT);
					ra.addFlashAttribute("error", messageSource.getMessage("error", null, Locale.getDefault()));
				}
			}
		}
		return "redirect:/" + URLConstant.URL_ADMIN_PRODUCT_PICTURE_REDIRECT;
	}

	@GetMapping(URLConstant.URL_ADMIN_PRODUCT_PICTURE_DELETE)
	public String delPicture(@PathVariable int id, Model model, RedirectAttributes ra, HttpServletRequest request) {
		ImageProduct picture = imageProductService.findById(id);
		Product product = productService.findById(picture.getProduct().getId());
		StringBuilder sb = new StringBuilder();
		sb.append("redirect:/admin/san-pham/hinh-anh-san-pham/").append(StringUtil.makeSlug(product.getName()))
				.append("-").append(product.getId()).append(".html");
		if (imageProductService.totalRowByProductId(picture.getProduct().getId()) == GlobalConstant.MIN_PICTURE) {
			ra.addFlashAttribute("error", messageSource.getMessage("minPictureError", null, Locale.getDefault()));
			return sb.toString();
		}
		if (imageProductService.del(id) > 0) {
			FileUtil.delFile(picture.getPicture(), request, GlobalConstant.DIR_UPLOAD_PICTURE_PRODUCT);
			ra.addFlashAttribute("success", messageSource.getMessage("delPictureSuccess", null, Locale.getDefault()));
		} else {
			ra.addFlashAttribute("error", messageSource.getMessage("error", null, Locale.getDefault()));
		}
		return sb.toString();
	}

	@GetMapping(URLConstant.URL_ADMIN_PRODUCT_UPDATE)
	public String update(@PathVariable int id, Model model, RedirectAttributes ra) {
		model.addAttribute("listCat", categoryService.getAll());
		Product product = productService.findById(id);
		if (product == null) {
			ra.addFlashAttribute("msg", messageSource.getMessage("noDataProduct", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_PRODUCT_INDEX_REDIRECT;
		}
		model.addAttribute("product", product);
		return ViewNameConstant.ADMIN_PRODUCT_UPDATE;
	}

	@PostMapping(URLConstant.URL_ADMIN_PRODUCT_UPDATE)
	public String update(@Valid @ModelAttribute("productError") Product product, BindingResult productRs, Model model,
			RedirectAttributes ra, HttpServletRequest request) {
		model.addAttribute("listCat", categoryService.getAll());
		model.addAttribute("product", product);
		productValidate.validateCat(product.getCat().getId(), productRs);
		if (productRs.hasErrors()) {
			return ViewNameConstant.ADMIN_PRODUCT_UPDATE;
		}
		if (productService.update(product) > 0) {
			ra.addFlashAttribute("success",
					messageSource.getMessage("updateProductSuccess", null, Locale.getDefault()));
		} else {
			ra.addFlashAttribute("error", messageSource.getMessage("error", null, Locale.getDefault()));
		}
		return "redirect:/" + URLConstant.URL_ADMIN_PRODUCT_INDEX_REDIRECT;
	}

	@Transactional
	@GetMapping(URLConstant.URL_ADMIN_PRODUCT_DELETE)
	public String del(@PathVariable int id, Model model, RedirectAttributes ra, HttpServletRequest request) {
		List<ImageProduct> pictureList = imageProductService.findByProductId(id);
		if (imageProductService.delByProductId(id) > 0) {
			System.out.println();
		}
		if (productService.del(id) > 0) {
			if (reviewsService.delByProductId(id) > 0) {
				System.out.println();
			}
			FileUtil.delMultipleFile(pictureList, request, GlobalConstant.DIR_UPLOAD_PICTURE_PRODUCT);
			ra.addFlashAttribute("success", messageSource.getMessage("delProductSuccess", null, Locale.getDefault()));
		} else {
			ra.addFlashAttribute("error", messageSource.getMessage("error", null, Locale.getDefault()));
		}
		return "redirect:/" + URLConstant.URL_ADMIN_PRODUCT_INDEX_REDIRECT;
	}

}