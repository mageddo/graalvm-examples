package com.mageddo.demo.controller;

import com.mageddo.rawstringliterals.RawString;
import com.mageddo.rawstringliterals.Rsl;
import org.junit.jupiter.api.Test;
import spark.Spark;

import static com.mageddo.rawstringliterals.RawStrings.lateInit;

@Rsl
public class BookmarkControllerTest {

	@Test
	public void TestGetV1_0Success(){
		Spark.port(48484);

		/*
		<?xml version="1.0" encoding="UTF-8"?>
		<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
			<url>
				<loc>/bookmark/1/x</loc>
				<lastmod>2017-08-07</lastmod>
				<changefreq>weekly</changefreq>
				<priority>1</priority>
			</url>
		</urlset>
		 */
		@RawString
		final String xml = lateInit();


//		now, err := time.Parse("2006-01-02", "2017-08-07")


//
//		service.NewBookmarkService().SaveBookmark(entity.NewBookmarkWithNameAndVisibility("X", entity.PUBLIC))
//
//		resp, c, err := test.NewReq("GET", "/api/v1.0/sitemap")
//
//		assert.Nil(t, err)
//		assert.Equal(t, 200, c)
//
//		resp = regex.ReplaceAllString(resp, "")
//
//		assert.Equal(t, len(expectedXML), len(resp))
//		assert.Equal(t, expectedXML, resp)

	}

}

//
//	var regex = regexp.MustCompile("[\t\n]+")
//
//	func TestGetV1_0Success(t *testing.T){
//
//		now, err := time.Parse("2006-01-02", "2017-08-07")
//		if err != nil {
//			t.Fatal(err)
//		}
//		utils.SetNow(now)
//
//		test.BuildDatabase()
//
//		expectedXML := regex.ReplaceAllString(`<?xml version="1.0" encoding="UTF-8"?>
//<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
//	<url>
//		<loc>/bookmark/1/x</loc>
//		<lastmod>2017-08-07</lastmod>
//		<changefreq>weekly</changefreq>
//		<priority>1</priority>
//	</url>
//</urlset>
//`, "")
//
//
//		service.NewBookmarkService().SaveBookmark(entity.NewBookmarkWithNameAndVisibility("X", entity.PUBLIC))
//
//		resp, c, err := test.NewReq("GET", "/api/v1.0/sitemap")
//
//		assert.Nil(t, err)
//		assert.Equal(t, 200, c)
//
//		resp = regex.ReplaceAllString(resp, "")
//
//		assert.Equal(t, len(expectedXML), len(resp))
//		assert.Equal(t, expectedXML, resp)
//
//	}
//
//	func TestGetV1_0CustomBaseURLSuccess(t *testing.T){
//
//		now, err := time.Parse("2006-01-02", "2017-08-07")
//		if err != nil {
//			t.Fatal(err)
//		}
//		utils.SetNow(now)
//
//		test.BuildDatabase()
//
//		expectedXML := regex.ReplaceAllString(`<?xml version="1.0" encoding="UTF-8"?>
//<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
//	<url>
//		<loc>acme.com/bookmark/2/x2</loc>
//		<lastmod>2017-08-07</lastmod>
//		<changefreq>weekly</changefreq>
//		<priority>1</priority>
//	</url>
//	<url>
//		<loc>acme.com/bookmark/1/x</loc>
//		<lastmod>2017-08-07</lastmod>
//		<changefreq>weekly</changefreq>
//		<priority>1</priority>
//	</url>
//</urlset>
//`, "")
//
//
//		service.NewBookmarkService().SaveBookmark(entity.NewBookmarkWithNameAndVisibility("X", entity.PUBLIC))
//		service.NewBookmarkService().SaveBookmark(entity.NewBookmarkWithNameAndVisibility("X2", entity.PUBLIC))
//		service.NewBookmarkService().SaveBookmark(entity.NewBookmarkWithNameAndVisibility("X3", entity.PRIVATE))
//
//		resp, c, err := test.NewReq("GET", "/api/v1.0/sitemap?url=acme.com")
//
//		assert.Nil(t, err)
//		assert.Equal(t, 200, c)
//
//		resp = regex.ReplaceAllString(resp, "")
//
//		assert.Equal(t, len(expectedXML), len(resp))
//		assert.Equal(t, expectedXML, resp)
//
//	}
