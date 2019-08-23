package kiwi.blog.category.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kiwi.blog.category.model.request.CategoriesRequest;
import kiwi.blog.category.model.request.SaveCategoryRequests;
import kiwi.blog.category.model.response.CategoriesResponse;
import kiwi.blog.category.model.response.CategoryResponse;
import kiwi.blog.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "Category", description = "카테고리")
@RestController
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasRole('sdvsdvsdv')")
    @GetMapping
    public ResponseEntity<CategoriesResponse> getCategories(@ModelAttribute CategoriesRequest categoriesRequest, @AuthenticationPrincipal OAuth2Authentication auth) {

        Map user = (Map) ((OAuth2AuthenticationDetails) auth.getDetails()).getDecodedDetails();
        long memberNo = Long.valueOf(String.valueOf(user.get("member_seq")));

        CategoriesResponse response = categoryService.getCategories(categoriesRequest);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("{categoryNo}")
    @ApiOperation(value = "카테고리 조회", notes = "카테고리를 조회합니다.")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable long categoryNo) {
        CategoryResponse response = categoryService.getCategoryResponse(categoryNo);

        if (response == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    @ApiOperation(value = "카테고리 저장/수정", notes = "카테고리를 저장/수정합니다")
    public ResponseEntity<?> saveCategories(@RequestBody SaveCategoryRequests saveCategoryRequests) {

        categoryService.saveCategories(saveCategoryRequests);

        return ResponseEntity.noContent().build();
    }
}
