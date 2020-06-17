using APINET_Vinted.Repositories;
using APINET_Vinted.Services;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.FileProviders;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.PlatformAbstractions;
using Microsoft.OpenApi.Models;
using System.IO;

namespace APINET_Vinted
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            //Añadir DBContext de la aplicación registrado BD SQL Server 2017 Express
            services.AddDbContext<VINTEDContext>(options => options.UseSqlServer(Configuration.GetConnectionString("ApplicationDbContext")));

            //Habilitar CORS para usar desde otro servidor
            services.AddCors(options =>
            {
                options.AddPolicy("AllowAll",
                    p => p.AllowAnyOrigin().
                    AllowAnyMethod().
                    AllowAnyHeader().
                    AllowCredentials());
            });

            services.AddScoped<IVINTEDContext, VINTEDContext>();


            services.AddScoped<IUsuarioService, UsuarioService>();
            services.AddScoped<IUsuarioRepository, UsuarioRepository>();

            services.AddScoped<IProductoService, ProductoService>();
            services.AddScoped<IProductoRepository, ProductoRepository>();

            services.AddScoped<IPedidoService, PedidoService>();
            services.AddScoped<IPedidoRepository, PedidoRepository>();

            services.AddControllers();

            var filePath = Path.Combine(PlatformServices.Default.Application.ApplicationBasePath, "APINET_Vinted.xml");

            services.AddSwaggerGen(config =>
                    config.SwaggerDoc("v1", new OpenApiInfo()
                    {
                        Title = "VINTED API Documentacion",                      
                    }));

            services.AddSwaggerGen(config =>
                    config.IncludeXmlComments(filePath));

        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            //app.UseHttpsRedirection();

            app.UseStaticFiles();

            app.UseStaticFiles(new StaticFileOptions
            {
                FileProvider = new PhysicalFileProvider(
          Path.Combine(Directory.GetCurrentDirectory(), "wwwroot" , "images", "productos")),
                RequestPath = "/productos/images"
            });

            app.UseRouting();

            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });

            //Swagger Middleware
            app.UseSwagger();
            app.UseSwaggerUI(config => 
                config.SwaggerEndpoint("/swagger/v1/swagger.json", "API de ejemplo SWAGGER"));
        }
    }
}
